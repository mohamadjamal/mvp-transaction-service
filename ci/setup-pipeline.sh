## Login to fly

fly -t main login -c <Concourse URL> -u test -p test

## Setup pipeline

fly -t main set-pipeline -c pipeline.yml -p mvp-transaction-service-pipe
