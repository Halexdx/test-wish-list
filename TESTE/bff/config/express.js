const express    = require('express');
const bodyParser = require('body-parser');
const config     = require('config');
const ApiController = require('../controllers/ApiController')
const cors = require('cors');

module.exports = () => {
  const app = express();

  app.use(cors());
  app.use(bodyParser.json());
  app.use(bodyParser.urlencoded({ extended: false }));
  app.use("/api", cors(), ApiController);

  // SETANDO VARIÁVEIS DA APLICAÇÃO
  app.set('port', process.env.PORT || config.get('server.port'));

  // MIDDLEWARES
  app.use(bodyParser.json());

  return app;
};