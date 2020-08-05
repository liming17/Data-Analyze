module.exports = {
    devServer: {
      port: 8081,
      proxy: {
        "/posts": {
          target: "http://localhost:8080",
          secure: false
        },
        "/oauth/token": {
          target: "http://localhost:8080",
          secure: false
        },
        "/register": {
          target: "http://localhost:8080",
          secure: false
        }
      }
    }
  };