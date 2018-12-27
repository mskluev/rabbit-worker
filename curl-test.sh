#!/bin/bash
# timing crafted to show jobs are not distributed round-robin
curl -d '{"name":"Job1", "length":"15"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/rabbit
curl -d '{"name":"Job2", "length":"10"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/rabbit
curl -d '{"name":"Job3", "length":"05"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/rabbit
curl -d '{"name":"Job4", "length":"15"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/rabbit
curl -d '{"name":"Job5", "length":"15"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/rabbit
curl -d '{"name":"Job6", "length":"15"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/rabbit