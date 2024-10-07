package progressa.progressaspring.controllers;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;

/**
 * @author danielfpc11@gmail.com
 */
public class BaseControllerTest {

    /**
     * Assert that a servlet exception is thrown during the execution of a given executable.
     *
     * @param causeClass The class of the expected cause of the servlet exception.
     * @param executable The executable code block expected to throw the {@link ServletException}.
     */
    protected void assertServletException(final Class<?> causeClass, final Executable executable) {
        final ServletException servletException = Assertions.assertThrows(ServletException.class, executable);
        Assertions.assertEquals(causeClass, servletException.getCause()
                                                            .getClass());
    }

    /**
     * Returns the json body of the given {@link ResultActions} as String.
     *
     * @param resultActions that contains the json body.
     * @return the json body in String format.
     * @throws UnsupportedEncodingException if there is an error with the encoding of the body.
     */
    protected String getJson(final ResultActions resultActions) throws UnsupportedEncodingException {
        return resultActions.andReturn()
                            .getResponse()
                            .getContentAsString();
    }

}
