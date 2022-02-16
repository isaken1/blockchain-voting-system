import { useEffect, useState } from 'react';
import Web3 from 'web3';

export default function useAuth() {
  const [account, setAccount] = useState({});

  const loadBlockchainData = async () => {
    const web3 = new Web3(Web3.givenProvider || 'http://localhost:7545');
    const accounts = await web3.eth.getAccounts();
    setAccount({ account: accounts[0] });
  };

  useEffect(() => {
    loadBlockchainData();
  }, [])

  return { account };
}