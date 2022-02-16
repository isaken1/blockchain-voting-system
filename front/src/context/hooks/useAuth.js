import { useEffect, useState } from 'react';

import api from '../../api';

export default function useAuth() {
  const [authenticated, setAuthenticated] = useState(false);
  const [loading, setLoading] = useState(false);
  const [voterName, setVoterName] = useState(null);
  const [hashedCpf, setHashedCpf] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('token');

    setLoading(true);
    
    if (token) {
      api.defaults.headers.Authorization = `Bearer ${JSON.parse(token)}`;
      setAuthenticated(true);
    }

    setLoading(false);
  }, []);

  async function handleLogin(inputCpf) {
    const { data: { token, name, cpf } } = await api.post('/login', { cpf: inputCpf });

    setVoterName(name);
    setHashedCpf(cpf);

    localStorage.setItem('token', JSON.stringify(token));
    api.defaults.headers.Authorization = `Bearer ${token}`
    setAuthenticated(true);
    //history.pushState('/elections');
  }

  return { authenticated, loading, handleLogin, voterName, hashedCpf };
}