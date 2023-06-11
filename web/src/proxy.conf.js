const PROXY_CONFIG = [
  {
    context: [
      "/api",
    ],
    target: "http://localhost:4213",
    secure: false
  }
]

module.exports = PROXY_CONFIG;
