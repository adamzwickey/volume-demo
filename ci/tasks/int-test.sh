#!/bin/bash

set -e

export NEXT_APP_COLOR=$(cat ./current-app-info/next-app.txt)
export NEXT_APP_URL=http://$CF_APP-$NEXT_APP_COLOR.$CF_APP_DOMAIN/

echo "Running curl integration tests..."

curl --include -k $NEXT_APP_URL | grep "HTTP/1.1 200 OK" > result.txt
export RESULT=$(cat result.txt)
if [[ $RESULT =~ .*OK.* ]]
then
  return 0
else
  return 1
fi
