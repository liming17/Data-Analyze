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
        },
        "/logoutCurrentUser": {
          target: "http://localhost:8080",
          secure: false
        },
        "/post": {
          target: "http://localhost:8080",
          secure: false
        },
        "/post/postComment": {
          target: "http://localhost:8080",
          secure: false
        },
        "/comments": {
          target: "http://localhost:8080",
          secure: false
        },
        "/stock": {
          target: "http://localhost:8080",
          secure: false
        },
        "/stockList": {
          target: "http://localhost:8080",
          secure: false
        }
      }
    }
  };