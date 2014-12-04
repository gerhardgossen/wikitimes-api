package de.l3s.wikitimes.client;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

/**
 * Create the JSON parser used by Jersey.
 */
@Provider
class ObjectMapperProvider implements ContextResolver<ObjectMapper> {
    private final ObjectMapper mapper = createMapper();

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }

    private ObjectMapper createMapper() {
        return new ObjectMapper().registerModules(new ParameterNamesModule(), new Jdk8Module(),
            new JSR310Module());
    }

}
