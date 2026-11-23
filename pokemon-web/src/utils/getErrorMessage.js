export function getErrorMessage(error){
  if (!error) return 'Error desconocido';
  if (error.code==='ECONNABORTED') return 'La petición ha expirado';
  const status=error.response?.status;
  const msg=error.response?.data?.message||error.message;
  if (status===400) return `Solicitud inválida: ${msg}`;
  if (status===401) return `No autorizado: ${msg}`;
  if (status===403) return `Prohibido: ${msg}`;
  if (status===404) return `No encontrado: ${msg}`;
  if (status===500) return `Error interno del servidor: ${msg}`;
  return msg||'Error inesperado';
}
