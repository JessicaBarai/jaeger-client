package com.techprimers.jaegerclient.config;

import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ConstSampler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

//"http://jaeger-server-git:8082"
//this is a configuration where we going to inject two beans
@Configuration
public class JaegerConfig {
//WebClient is going to be used to connect client to server, , reactive client to perform HTTP requests, exposing a fluent, reactive API over underlying HTTP client libraries such as Reactor Netty.
	@Bean
	public WebClient webClient() throws IOException {
		return return WebClient.create("http://jaeger-server-git:8082");
		
		/*String filePath="./resources/application.properties";
		Properties pros;
		String url;
		pros= new Properties();
		FileInputStream ip= new FileInputStream(filePath);
		pros.load(ip);
		url=pros.getProperty("url");
		System.out.println("url from properties"+ url);
		return WebClient.create(url);*/
		
	}

	//this is wrong, it should listen to properties file or we should use enviroment variable. Look it up!
	//with this code it is running by deafult at localhost. Make sure it listenes to enviroment variable 
	// ask maciej tomrrow!
	@Bean
	public JaegerTracer jaegerTracer() {
	SamplerConfiguration samplerConfig = SamplerConfiguration.fromEnv().withType(ConstSampler.TYPE).withParam(1);
			  ReporterConfiguration reporterConfig = ReporterConfiguration.fromEnv().withLogSpans(true).withSender(
			      SenderConfiguration.fromEnv());
			  io.jaegertracing.Configuration config =  new io.jaegertracing.Configuration("jaeger-client").withSampler(samplerConfig).withReporter(reporterConfig);
			  System.out.println(reporterConfig.getSenderConfiguration());
			  return config.getTracer();	
			 /*SamplerConfiguration samplerConfig = SamplerConfiguration.fromEnv().withType(ConstSampler.TYPE).withParam(1);
			  ReporterConfiguration reporterConfig = ReporterConfiguration.fromEnv().withLogSpans(true).withSender(
			      SenderConfiguration.fromEnv().withAgentHost("jaeger-all-in-one-inmemory-agent").withAgentPort(6831));
			  io.jaegertracing.Configuration config =  new io.jaegertracing.Configuration("jaeger-client").withSampler(samplerConfig).withReporter(reporterConfig);
// 			  return config.getTracer();*/
			
		
	//return new io.jaegertracing.internal.JaegerTracer.Builder("jaeger-server").build();

	/*return new io.jaegertracing.Configuration("jaeger-server")
				.withSampler(new io.jaegertracing.Configuration.SamplerConfiguration().withType(ConstSampler.TYPE)
						.withParam(1))
				.withReporter(new io.jaegertracing.Configuration.ReporterConfiguration().withLogSpans(true))
				.getTracer();*/
	}

}






/*package com.techprimers.jaegerclient.config;

import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ConstSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class JaegerConfig {

  @Bean
  public WebClient webClient() {
    return WebClient.create("http://localhost:8082");
  }
//this is a help function, this function creates a tracer 
  @Bean
  public JaegerTracer jaegerTracer() {

    return new io.jaegertracing.Configuration("jaeger-client")
        .withSampler(new io.jaegertracing.Configuration.SamplerConfiguration().withType(ConstSampler.TYPE)
        .withParam(1))
        .withReporter(new io.jaegertracing.Configuration.ReporterConfiguration().withLogSpans(true))
        .getTracer();
  }
}
*/
