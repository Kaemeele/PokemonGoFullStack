import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './pages/Login.jsx';
import PokemonList from './pages/PokemonList.jsx';
import AdminBasicList from './pages/AdminBasicList.jsx';
import PokemonEditor from './pages/PokemonEditor.jsx';
import ProtectedRoute from './components/ProtectedRoute.jsx';
function App(){
  return(
    <Router>
      <Routes>
        <Route path="/login" element={<Login/>} />
        <Route path="/" element={<PokemonList/>} />
        <Route path="/admin" element={<ProtectedRoute component={AdminBasicList}/> } />
        <Route path="/new" element={<ProtectedRoute component={PokemonEditor}/> } />
        <Route path="/edit/:id" element={<ProtectedRoute component={PokemonEditor}/> } />
      </Routes>
    </Router>
  );
}
export default App;