const express = require('express');
const router = express.Router();
const ApiService = require('../services/ApiService');
const apiService = new ApiService();

router.get('/items', async (req, res) => {
  try {
    const items = await apiService.getProducts();
    res.json({ items });
  } catch (error) {
    console.error('Erro ao listar produtos:', error);
    res.status(404).json({ error: 'Erro not found' });
  }
});

router.get('/wishlist/:id', async (req, res) => {
  try {
    const id = req.params.id;
    const item = await apiService.getWish(id);
    res.json({ item });
  } catch (error) {
    console.error('Erro ao listar produtos do user:', req.params.id);
    res.status(404).json({ error: 'Erro lista de desejos não escontrada'});
  }
});

router.post('/wishlist', async (req, res) => {  
  try {
    const item = req.body;
    await apiService.createItem(item);
    res.json({ message: 'Novo item criado'});
  } catch (error) {
    console.error('Erro ao adicionar produtos a wishlist er:', error);
    res.status(500).json({ error: 'Erro ao adicionar produto'});
  }
});

router.delete('/wishlist/:id/:code', async (req, res) => {
  try {
    const userId = req.params.id;
    const code = req.params.code;
    await apiService.deleteItem(userId, code);
    res.json({ message: 'Item excluído com sucesso' });
  } catch (error) {
    console.error('Erro ao remover produtos a wishlist er:', error);
    res.status(500).json({ error: 'Erro ao remover produto'});
  }
});

module.exports = router;