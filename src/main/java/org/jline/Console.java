/*
 * Copyright (c) 2002-2015, the original author or authors.
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package org.jline;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.PrintWriter;

import org.fusesource.jansi.Pty.Attributes;
import org.fusesource.jansi.Pty.Size;
import org.jline.console.NativeSignalHandler;
import org.jline.utils.InfoCmp.Capability;
import org.jline.utils.NonBlockingReader;

public interface Console extends Closeable, Flushable {

    //
    // Signal support
    //

    enum Signal {
        INT,
        QUIT,
        TSTP,
        CONT,
        INFO,
        WINCH
    }

    interface SignalHandler {

        SignalHandler SIG_DFL = NativeSignalHandler.SIG_DFL;
        SignalHandler SIG_IGN = NativeSignalHandler.SIG_IGN;

        void handle(Signal signal);
    }

    SignalHandler handle(Signal signal, SignalHandler handler);

    void raise(Signal signal);

    //
    // Input / output
    //

    NonBlockingReader reader();

    PrintWriter writer();

    //
    // Pty settings
    //

    Attributes enterRawMode() throws IOException;

    boolean echo() throws IOException;

    boolean echo(boolean echo) throws IOException;

    Attributes getAttributes() throws IOException;

    void setAttributes(Attributes attr) throws IOException;

    void setAttributes(Attributes attr, int actions) throws IOException;

    Size getSize() throws IOException;

    void setSize(Size size) throws IOException;

    //
    // Infocmp capabilities
    //

    String getType();

    boolean puts(Capability capability, Object... params) throws IOException;

    boolean getBooleanCapability(Capability capability);

    Integer getNumericCapability(Capability capability);

    String getStringCapability(Capability capability);

}
