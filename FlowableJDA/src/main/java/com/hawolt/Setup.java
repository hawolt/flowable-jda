package com.hawolt;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created: 22/04/2022 23:39
 * Author: Twitter @hawolt
 **/

public class Setup {

    private JsonSource source;

    protected Setup(String[] args) {
        Parser parser = new Parser();
        parser.add(Argument.create("c", "config", "specify path to config", true, true, false));
        try {
            CLI cli = parser.check(args);
            String config = cli.getValue("config");
            this.source = JsonSource.of(Paths.get(config));
            if (this.source.containsKey("sql")) Hikari.setup(source);
        } catch (ParserException e) {
            System.err.println(e.getMessage());
            System.err.println(parser.getHelp());
        } catch (IOException e) {
            Logger.error(e);
        }
    }

    public JsonSource getSource() {
        return source;
    }
}
