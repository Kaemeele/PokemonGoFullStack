import React from 'react';
import PokemonForm from './PokemonForm.jsx';
import { useParams } from 'react-router-dom';
function PokemonEditor(){
  const { id } = useParams();
  const isEdit = Boolean(id);
  return <PokemonForm isEdit={isEdit} />;
}
export default PokemonEditor;
