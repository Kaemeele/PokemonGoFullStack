import httpClient from '../api/httpClient.js';
export async function listPublic(){
  const response=await httpClient.get('/v2/pokemons');
  return response.data;
}
export async function listBasicAdmin(){
  const response=await httpClient.get('/v1/pokemons');
  return response.data;
}
export async function findById(id){
  const response=await httpClient.get(`/v2/pokemons/${id}`);
  return response.data;
}
export async function create(pokemon){
  const response=await httpClient.post('/v1/pokemons',pokemon);
  return response.data;
}
export async function update(id,pokemon){
  const response=await httpClient.put(`/v2/pokemons/${id}`,pokemon);
  return response.data;
}
export async function remove(id){
  await httpClient.delete(`/v2/pokemons/${id}`);
}
