import React, { useContext, useState } from 'react';
import { Button, Form } from 'react-bootstrap';

import { Context } from '../context/ContextProvider';

export default function Login() {

  const [cpf, setCpf] = useState("");

  const { handleLogin } = useContext(Context);

  const cpfMask = value => (
    value.replace(/\D/g, '') // substitui qualquer caracter que nao seja numero por nada
      .replace(/(\d{3})(\d)/, '$1.$2') // captura 2 grupos de numeros o primeiro de 3 e o segundo de 1, apos capturar o primeiro grupo ele adiciona um ponto antes do segundo grupo de numero
      .replace(/(\d{3})(\d)/, '$1.$2')
      .replace(/(\d{3})(\d{1,2})/, '$1-$2')
      .replace(/(-\d{2})\d+?$/, '$1') // captura 2 numeros seguidos de um traço e não deixa ser digitado mais nada
  );

  const handleInputChange = (e) => {
    setCpf(cpfMask(e.target.value));
  }

  const validateForm = () => ( cpf.length > 0);

  const handleSubmit = () => {
    console.log('SUBMIT!')
    console.log(cpf);
    handleLogin(cpf);
  }

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group size="lg" controlId="formLogin.cpf" className="mb-2">
        <Form.Label>CPF</Form.Label>
        <Form.Control
          required
          autoFocus
          type="text"
          value={cpf}
          onChange={handleInputChange}
        />

      </Form.Group>
      <div className='d-grid gap-2'>
        <Button className='btn btn-primary btn-block' type="submit" disabled={!validateForm()}>
          Entrar
        </Button>
      </div>
    </Form>

  );
}