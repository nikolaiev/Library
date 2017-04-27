package com.controller;

import com.controller.commands.Command;
import com.controller.commands.FindBookCommand;
import com.controller.commands.GetStaticFileCommand;
import com.controller.commands.GoInvalidUrlCommand;
import com.controller.commands.admin.book.AdminUpdateBookSubmitCommand;
import com.controller.commands.login.LoginCommand;
import com.controller.commands.login.LoginSubmitCommand;
import com.controller.commands.login.LogoutCommand;
import com.controller.commands.user.ProcessOrderListCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.controller.constants.JspPathsConst.ADMIN_BOOKS_VIEW;
import static com.controller.constants.JspPathsConst.USER_BOOKS_VIEW;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by vlad on 27.04.17.
 */
@RunWith(Parameterized.class)
public class CommandFabricTest {
    private static final String POST_PATH="POST:";
    private static final String GET_PATH="GET:";

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { GET_PATH+"/login",  new LoginCommand()},
                { POST_PATH+"/login",new LoginSubmitCommand()},
                { GET_PATH+"/user/books?some_int_get_param=123", new FindBookCommand(USER_BOOKS_VIEW)},
                { GET_PATH+"/admin/books", new FindBookCommand(ADMIN_BOOKS_VIEW) },
                { GET_PATH+"/logout", new LogoutCommand()},
                { POST_PATH+"/user/process", new ProcessOrderListCommand()},
                { POST_PATH+"/admin/book/update?param=1", new AdminUpdateBookSubmitCommand()},
                { GET_PATH+"/static/some_image.png", new GetStaticFileCommand()},
                { GET_PATH+"/wrong_url", new GoInvalidUrlCommand()},
                { POST_PATH+"/wrong_url", new GoInvalidUrlCommand()}
        });
    }

    private String url;
    private Command expectedCommand;

    public CommandFabricTest(String url,Command expectedCommand){
        this.url=url;
        this.expectedCommand=expectedCommand;
    }

    @Test
    public void getCommand() throws Exception {
        Command actualCommand=CommandFabric.getInstance().getCommand(url);
        assertEquals(expectedCommand.getClass(),actualCommand.getClass());
    }
}