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

const t = new Uint8Array([0x89, 0x82, 0x01, 0x01, 0x01, 0x01, 0x01 ^ 0x68, 0x01 ^ 0x69]);

proxy.on('open', function(soc) {
    console.log('open evt');
    setInterval(function(s) {
        try {
            if (!s.writable) {
                console.log('not writable');
                clearInterval(this);
            } else {
                s.write(t);
            }
        } catch (err) {
            console.log(err);
            clearInterval(this);
        }
    }, 5 * 1000, soc);
})

console.log("listening on port 5050")
server.listen(5050);
