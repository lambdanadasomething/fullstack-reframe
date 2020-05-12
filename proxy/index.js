var httpProxy = require('http-proxy');
var http = require('http');

var options = {
  target: {
    host: 'localhost',
    port: 9630
  }
};
var proxy = httpProxy.createProxyServer(options);

var server = http.createServer(function(req, res) {
    proxy.on('error', function(e) {
        console.log(e);
    });
    proxy.web(req, res);
});

server.on('upgrade', function (req, socket, head) {
    console.log(req.url);
    proxy.ws(req, socket, head);
});

console.log("listening on port 5050")
server.listen(5050);
