#!/bin/bash
echo "Aguardando MongoDB ficar pronto..."

until mongosh --host mongo --eval "db.runCommand({ ping: 1 })" > /dev/null 2>&1; do
    echo "MongoDB ainda não está pronto. Esperando mais 5 segundos..."
    sleep 5
done

echo "MongoDB está pronto! Iniciando a aplicação..."
exec java -jar app.jar