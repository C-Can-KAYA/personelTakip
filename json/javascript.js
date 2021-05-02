var express = require('express');
var app = express();
var mysql = require('mysql');

var connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '',
  database: 'personeltakip'
});
app.get('/listele', function(req, res) {
  console.log(req.query);
  var tabloAdi = req.query.tablo;
  connection.query('SELECT * from ' + tabloAdi, function(error, results, fields) {
    if (error) throw error;
    res.end(JSON.stringify(results));
  });
});