#!/bin/bash
 
if [ -z "$1" ]; then
  echo "Usage: ./start.sh <port>"
  echo "Example: ./start.sh 3000"
  exit 1
fi
 
SERVER_PORT=$1
 
if [ ! -f .env ]; then
  if [ ! -f .env.example ]; then
    echo "Error: .env.example not found"
    exit 1
  fi
  echo "Creating .env from .env.example..."
  cp .env.example .env
fi
 
SERVER_PORT=$SERVER_PORT docker compose up -d --build
 