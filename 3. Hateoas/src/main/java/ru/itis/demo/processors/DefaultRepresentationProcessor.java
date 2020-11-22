package ru.itis.demo.processors;


import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class DefaultRepresentationProcessor implements RepresentationModelProcessor<RepositoryLinksResource> {
    @Override
    public RepositoryLinksResource process(RepositoryLinksResource model) {
        model.add(Link.of("http://localhost:8080/hello", "hello"));
        return model;
    }
}
