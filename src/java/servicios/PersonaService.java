/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;

/**
 * Jersey REST client generated for REST resource:Persona [persona]<br>
 * USAGE:
 * <pre>
 *        PersonaService client = new PersonaService();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Portatil
 */
public class PersonaService {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/AgendaRest/webresources";

    public PersonaService() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("persona");
    }

    public void borrar(String idPersona) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{idPersona})).request().delete();
    }

    public void actualizar(Object requestEntity, String idPersona) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{idPersona})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public <T> T getXml(Class<T> responseType, String idAgenda, String nombre, String token) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (nombre != null) {
            resource = resource.queryParam("nombre", nombre);
        }
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{idAgenda}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).header(HttpHeaders.AUTHORIZATION, token).get(responseType);
    }

    public void putXml(Object requestEntity, String idAgenda) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{idAgenda})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void close() {
        client.close();
    }
    
}
