# riemannConfig
* Push based model to metrics using riemann
* There always has been talk about Pull Vs Push model in collecting the metric
  * Pull model mostly goes for prometheus
  * This project explain about push based
    * How pipeline works in this case is SpringBoot Application -> riemann -> InfluxDB
    * This project has configuration related riemann server
* Please follow link to understand the in sample point of view: https://www.kartar.net/2014/12/an-introduction-to-riemann/     
* This project will have sample config file with InfluxDB and logging event
* This project has two files mentioned in Folder ('riemannConfig'). 
    * Install riemann on system using : https://brewinstall.org/Install-riemann-on-Mac-with-Brew/
    * you can find the config @ /usr/local/etc/riemann.config
        * if not then create this file using the config mentioned in folder mentioned and restart the riemann using
            ```brew services restart riemann```
    * if  riemann is started , tail log files of riemann
    * Start this application and you will see the metrics into logging file.
        * Sample of logging is :  
            ```
          INFO [2020-12-30 19:39:38,821] defaultEventExecutorGroup-2-2 - riemann.config - #riemann.codec.Event{:host PP-C02D1513MD6M.local, :service test_prefix.org.eclipse.jetty.server.HttpConnectionFactory.8186.connections.m15_rate, :state nil, :description nil, :metric 0.0, :tags [YOUR_APPLICATION_TAG], :time 1609337378, :ttl nil, :app YOUR_APPLICATION_TAG, :hostname localhost, :metric-type Timer, :env local}
            ```      