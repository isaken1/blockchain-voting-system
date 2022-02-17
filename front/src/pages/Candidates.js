import HDWalletProvider from '@truffle/hdwallet-provider';
import React, { useContext, useEffect, useState } from 'react';
import { ListGroup } from 'react-bootstrap';
import { useLocation, useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import Web3 from 'web3';

import api from '../api';
import { BVS_ABI, BVS_ADDRRESS } from '../blockchain_config';
import { Context } from '../context/ContextProvider';

export default function Candidates() {
  const [candidates, setCandidates] = useState([]);
  const { state } = useLocation();
  const [bvs, setBvs] = useState();

  const { hashedCpf } = useContext(Context);  
  const navigator = useNavigate();

  const insertVote = (voter, candidateId, electionId) => {
    return bvs.methods.insertVote(voter, candidateId, electionId).send({ from: '0x373E9a012EFF21234738989aEfa74deC2aD8C366' });
  }

  const onCandidatePressed = (candidate) => {
    Swal.fire({
      title: 'Confirmação',
      text: `Você tem certeza que quer votar no candidato ${candidate.name}?`,
      icon: 'info',
      confirmButtonText: 'Confirmar',
      confirmButtonColor: '#0fd43d',
      showCancelButton: true,
      cancelButtonText: 'Cancelar',
      cancelButtonColor: '#e0260d',
    }).then(({ isConfirmed }) => {
      if (isConfirmed) {
        insertVote(hashedCpf, candidate.candidateId, candidate.electionId)
          .on('transactionHash', (payload) => {
              console.log(payload);
              Swal.fire({
                title: 'Sucesso',
                text: `Voto computado com sucesso`,
                icon: 'success',
                confirmButtonText: 'Retornar',
                confirmButtonColor: '#0fd43d',
              }).then(() => {
                navigator('/elections');
              });
          })
          .on('error', (payload) => {
            console.log(payload);
            Swal.fire({
              title: 'Erro',
              text: `Não foi possível computar seu voto`,
              icon: 'error',
              confirmButtonText: 'Retornar',
              confirmButtonColor: '#0fd43d',
            }).then(() => {
              navigator('/elections');
            });
          })
      }
    });
  }

  useEffect(() => {
    const fetchCandidates = async () => {
      const electionId = state;
      const { data } = await api.get(`/candidate/${electionId}`);
      setCandidates(data);
    }

    async function loadBlockchain() {
      const mnemonic = "someone eagle found father toast cheap add solid primary spare spice ride"

      const hd = new HDWalletProvider({ mnemonic, providerOrUrl: 'https://ropsten.infura.io/v3/ee9b562515134979bec7900f1bcda696' });
      const web3 = new Web3(hd);
      
      const bvs = new web3.eth.Contract(BVS_ABI, BVS_ADDRRESS);
      console.log(bvs);
      setBvs(bvs);
    }

    fetchCandidates();
    loadBlockchain();
  }, []);

  return (
    <div>
      <h5 style={{ textAlign: 'center' }}>Vote consciente!</h5>
      <ListGroup>
        {candidates.map(c => (
          <ListGroup.Item key={c.id} action onClick={() => onCandidatePressed(c)}>
            {c.name}
          </ListGroup.Item>
        ))}
      </ListGroup>
    </div>

  )
}