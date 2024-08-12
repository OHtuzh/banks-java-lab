package org.example.application.console.commands;

import lombok.NonNull;
import org.example.application.interfaces.IApplicationEngine;

public abstract class CommandBase implements ICommand {
    protected IApplicationEngine applicationEngine;

    protected CommandBase(@NonNull IApplicationEngine applicationEngine) {
        this.applicationEngine = applicationEngine;
    }
}
