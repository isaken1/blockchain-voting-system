import { useEffect, useState } from 'react';

import api from '../../api';

export default function useAuth() {
  const [loading, setLoading] = useState(false);
  const [voterName, setVoterName] = useState(undefined);
  const [hashedCpf, setHashedCpf] = useState(undefined);

  useEffect(() => {
    const token = localStorage.getItem('token');

    setLoading(true);
    
    if (token) {
      api.defaults.headers.Authorization = `${JSON.parse(token)}`;
    }

    setLoading(false);
  }, []);

  function isAuthenticated() {
    return localStorage.getItem('token') !== undefined;
  }

  async function handleLogin(inputCpf) {
    const response = await api.post('/login', { cpf: inputCpf });

    const { status } = response;
    if (status && status === 200) { 
      const { data: { token, name, cpf } } = response;
      setVoterName(name);
      setHashedCpf(cpf);

      localStorage.setItem('token', JSON.stringify(token));
      api.defaults.headers.Authorization = `${token}`;
    }
  }

  return { isAuthenticated, loading, handleLogin, voterName, hashedCpf };
}