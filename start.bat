@echo off
 
if "%~1"=="" (
    echo Usage: start.bat ^<port^>
    echo Example: start.bat 3000
    exit /b 1
)
 
set SERVER_PORT=%~1
 
if not exist .env (
    if not exist .env.example (
        echo Error: .env.example not found
        exit /b 1
    )
    echo Creating .env from .env.example...
    copy .env.example .env
)
 
set SERVER_PORT=%SERVER_PORT%

docker compose up -d --build
 