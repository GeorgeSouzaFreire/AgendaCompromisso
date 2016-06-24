/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2016-05-27 16:00:31 UTC)
 * on 2016-06-22 at 21:47:41 UTC 
 * Modify at your own risk.
 */

package br.poc.agenda.backend.acompanhamentoEndpoint;

/**
 * Service definition for AcompanhamentoEndpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link AcompanhamentoEndpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class AcompanhamentoEndpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.22.0 of the acompanhamentoEndpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://sharp-ring-133523.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "acompanhamentoEndpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public AcompanhamentoEndpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  AcompanhamentoEndpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "insertQuote".
   *
   * This request holds the parameters needed by the acompanhamentoEndpoint server.  After setting any
   * optional parameters, call the {@link InsertQuote#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso}
   * @return the request
   */
  public InsertQuote insertQuote(br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso content) throws java.io.IOException {
    InsertQuote result = new InsertQuote(content);
    initialize(result);
    return result;
  }

  public class InsertQuote extends AcompanhamentoEndpointRequest<br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso> {

    private static final String REST_PATH = "compromisso";

    /**
     * Create a request for the method "insertQuote".
     *
     * This request holds the parameters needed by the the acompanhamentoEndpoint server.  After
     * setting any optional parameters, call the {@link InsertQuote#execute()} method to invoke the
     * remote operation. <p> {@link
     * InsertQuote#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso}
     * @since 1.13
     */
    protected InsertQuote(br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso content) {
      super(AcompanhamentoEndpoint.this, "POST", REST_PATH, content, br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso.class);
    }

    @Override
    public InsertQuote setAlt(java.lang.String alt) {
      return (InsertQuote) super.setAlt(alt);
    }

    @Override
    public InsertQuote setFields(java.lang.String fields) {
      return (InsertQuote) super.setFields(fields);
    }

    @Override
    public InsertQuote setKey(java.lang.String key) {
      return (InsertQuote) super.setKey(key);
    }

    @Override
    public InsertQuote setOauthToken(java.lang.String oauthToken) {
      return (InsertQuote) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertQuote setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertQuote) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertQuote setQuotaUser(java.lang.String quotaUser) {
      return (InsertQuote) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertQuote setUserIp(java.lang.String userIp) {
      return (InsertQuote) super.setUserIp(userIp);
    }

    @Override
    public InsertQuote set(String parameterName, Object value) {
      return (InsertQuote) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listQuote".
   *
   * This request holds the parameters needed by the acompanhamentoEndpoint server.  After setting any
   * optional parameters, call the {@link ListQuote#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListQuote listQuote() throws java.io.IOException {
    ListQuote result = new ListQuote();
    initialize(result);
    return result;
  }

  public class ListQuote extends AcompanhamentoEndpointRequest<br.poc.agenda.backend.acompanhamentoEndpoint.model.CollectionResponseCompromisso> {

    private static final String REST_PATH = "compromisso";

    /**
     * Create a request for the method "listQuote".
     *
     * This request holds the parameters needed by the the acompanhamentoEndpoint server.  After
     * setting any optional parameters, call the {@link ListQuote#execute()} method to invoke the
     * remote operation. <p> {@link
     * ListQuote#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListQuote() {
      super(AcompanhamentoEndpoint.this, "GET", REST_PATH, null, br.poc.agenda.backend.acompanhamentoEndpoint.model.CollectionResponseCompromisso.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListQuote setAlt(java.lang.String alt) {
      return (ListQuote) super.setAlt(alt);
    }

    @Override
    public ListQuote setFields(java.lang.String fields) {
      return (ListQuote) super.setFields(fields);
    }

    @Override
    public ListQuote setKey(java.lang.String key) {
      return (ListQuote) super.setKey(key);
    }

    @Override
    public ListQuote setOauthToken(java.lang.String oauthToken) {
      return (ListQuote) super.setOauthToken(oauthToken);
    }

    @Override
    public ListQuote setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListQuote) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListQuote setQuotaUser(java.lang.String quotaUser) {
      return (ListQuote) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListQuote setUserIp(java.lang.String userIp) {
      return (ListQuote) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Integer count;

    /**

     */
    public java.lang.Integer getCount() {
      return count;
    }

    public ListQuote setCount(java.lang.Integer count) {
      this.count = count;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListQuote setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @Override
    public ListQuote set(String parameterName, Object value) {
      return (ListQuote) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeQuote".
   *
   * This request holds the parameters needed by the acompanhamentoEndpoint server.  After setting any
   * optional parameters, call the {@link RemoveQuote#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public RemoveQuote removeQuote(java.lang.Long id) throws java.io.IOException {
    RemoveQuote result = new RemoveQuote(id);
    initialize(result);
    return result;
  }

  public class RemoveQuote extends AcompanhamentoEndpointRequest<Void> {

    private static final String REST_PATH = "quote/{id}";

    /**
     * Create a request for the method "removeQuote".
     *
     * This request holds the parameters needed by the the acompanhamentoEndpoint server.  After
     * setting any optional parameters, call the {@link RemoveQuote#execute()} method to invoke the
     * remote operation. <p> {@link
     * RemoveQuote#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveQuote(java.lang.Long id) {
      super(AcompanhamentoEndpoint.this, "DELETE", REST_PATH, null, Void.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveQuote setAlt(java.lang.String alt) {
      return (RemoveQuote) super.setAlt(alt);
    }

    @Override
    public RemoveQuote setFields(java.lang.String fields) {
      return (RemoveQuote) super.setFields(fields);
    }

    @Override
    public RemoveQuote setKey(java.lang.String key) {
      return (RemoveQuote) super.setKey(key);
    }

    @Override
    public RemoveQuote setOauthToken(java.lang.String oauthToken) {
      return (RemoveQuote) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveQuote setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveQuote) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveQuote setQuotaUser(java.lang.String quotaUser) {
      return (RemoveQuote) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveQuote setUserIp(java.lang.String userIp) {
      return (RemoveQuote) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemoveQuote setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveQuote set(String parameterName, Object value) {
      return (RemoveQuote) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateQuote".
   *
   * This request holds the parameters needed by the acompanhamentoEndpoint server.  After setting any
   * optional parameters, call the {@link UpdateQuote#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso}
   * @return the request
   */
  public UpdateQuote updateQuote(br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso content) throws java.io.IOException {
    UpdateQuote result = new UpdateQuote(content);
    initialize(result);
    return result;
  }

  public class UpdateQuote extends AcompanhamentoEndpointRequest<br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso> {

    private static final String REST_PATH = "compromisso";

    /**
     * Create a request for the method "updateQuote".
     *
     * This request holds the parameters needed by the the acompanhamentoEndpoint server.  After
     * setting any optional parameters, call the {@link UpdateQuote#execute()} method to invoke the
     * remote operation. <p> {@link
     * UpdateQuote#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso}
     * @since 1.13
     */
    protected UpdateQuote(br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso content) {
      super(AcompanhamentoEndpoint.this, "PUT", REST_PATH, content, br.poc.agenda.backend.acompanhamentoEndpoint.model.Compromisso.class);
    }

    @Override
    public UpdateQuote setAlt(java.lang.String alt) {
      return (UpdateQuote) super.setAlt(alt);
    }

    @Override
    public UpdateQuote setFields(java.lang.String fields) {
      return (UpdateQuote) super.setFields(fields);
    }

    @Override
    public UpdateQuote setKey(java.lang.String key) {
      return (UpdateQuote) super.setKey(key);
    }

    @Override
    public UpdateQuote setOauthToken(java.lang.String oauthToken) {
      return (UpdateQuote) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateQuote setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateQuote) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateQuote setQuotaUser(java.lang.String quotaUser) {
      return (UpdateQuote) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateQuote setUserIp(java.lang.String userIp) {
      return (UpdateQuote) super.setUserIp(userIp);
    }

    @Override
    public UpdateQuote set(String parameterName, Object value) {
      return (UpdateQuote) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link AcompanhamentoEndpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link AcompanhamentoEndpoint}. */
    @Override
    public AcompanhamentoEndpoint build() {
      return new AcompanhamentoEndpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link AcompanhamentoEndpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setAcompanhamentoEndpointRequestInitializer(
        AcompanhamentoEndpointRequestInitializer acompanhamentoendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(acompanhamentoendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
