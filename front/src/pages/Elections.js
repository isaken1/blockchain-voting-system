import React, { useEffect, useState } from 'react';
import { ListGroup } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

import api from '../api';


export default function Elections() {
  const [elections, setElections] = useState([]);

  
  const navigator = useNavigate();

  useEffect(() => {
    const fetchElections = async () => {
      const data = await api.get('/election').then(res => (res.data));
      setElections(data);
    }

    fetchElections();
  }, []);


  const onElectionPressed = (electionId) => {
    let updatedElections = elections.map(e => {
      if (e.id === electionId) {
        e.voted = true;
      }
      return e;
    });
    setElections(updatedElections);
    navigator("/candidates", { state: electionId });
  }


  return (
    <div>
      <h5 style={{ textAlign: 'center' }}>Escolha uma eleição</h5>
      <ListGroup>
        {elections.map(e => (
          <ListGroup.Item key={e.id} action onClick={() => onElectionPressed(e.id)} disabled={e.voted}>
            {e.description}
          </ListGroup.Item>
        ))}
      </ListGroup>
    </div>
    
  )
}