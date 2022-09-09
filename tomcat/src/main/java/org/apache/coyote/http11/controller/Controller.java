package org.apache.coyote.http11.controller;

import java.io.IOException;
import org.apache.coyote.http11.request.HttpRequest;
import org.apache.coyote.http11.response.HttpResponse;

public interface Controller {

    boolean isSuitable(HttpRequest httpRequest);

    void service(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException;
}