## Login to fly

fly -t main login -c <Concourse URL> -u test -p test

## Setup pipeline

fly -t main sp -c pipeline.yml -p mvp-transaction-service-pipe -l credentials.yml
or
fly -t main set-pipeline --config pipeline.yml --pipeline mvp-transaction-service-pipe --load-vars-from credentials.yml
 
-t = --target
-c = --config
-p = --pipeline
-l = --load-vars-from
sp = set-pipeline