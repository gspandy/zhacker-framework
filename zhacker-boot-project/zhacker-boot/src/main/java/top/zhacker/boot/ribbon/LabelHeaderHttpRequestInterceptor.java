package top.zhacker.boot.ribbon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.util.StringUtils;

import java.io.IOException;


/**
 *
 */
public class LabelHeaderHttpRequestInterceptor implements ClientHttpRequestInterceptor {
	private static final Logger logger = LoggerFactory
			.getLogger(LabelHeaderHttpRequestInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {
		HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);

		String header = StringUtils.collectionToDelimitedString(
				LabelHeaderMvcHandlerInterceptor.label.get(),
				LabelHeaderMvcHandlerInterceptor.HEADER_LABEL_SPLIT);
		logger.info("label: " + header);
		requestWrapper.getHeaders().add(LabelHeaderMvcHandlerInterceptor.HEADER_LABEL, header);

		return execution.execute(requestWrapper, body);
	}
}
