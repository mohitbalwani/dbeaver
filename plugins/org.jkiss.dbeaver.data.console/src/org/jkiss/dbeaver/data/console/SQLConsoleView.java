/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2022 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.data.console;


import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.console.MessageConsole;
import org.jkiss.code.NotNull;
import org.jkiss.code.Nullable;
import org.jkiss.dbeaver.model.preferences.DBPPreferenceStore;
import org.jkiss.dbeaver.ui.DBeaverIcons;
import org.jkiss.dbeaver.ui.UIIcon;
import org.jkiss.dbeaver.ui.controls.resultset.ResultSetModel;
import org.jkiss.dbeaver.ui.controls.resultset.plaintext.PlainTextFormatter;
import org.jkiss.dbeaver.ui.editors.sql.SQLEditorOutputConsoleViewer;


public class SQLConsoleView extends SQLEditorOutputConsoleViewer {

    public SQLConsoleView(@NotNull IWorkbenchPartSite site, @NotNull CTabFolder tabsContainer, int styles) {
        super(site, tabsContainer, new MessageConsole("sql-data-log-output", DBeaverIcons.getImageDescriptor(UIIcon.SQL_CONSOLE)));
    }

    public void printQueryData(@NotNull DBPPreferenceStore prefs, @NotNull ResultSetModel model, @Nullable String name) {
        PlainTextFormatter formatter = new PlainTextFormatter(prefs);
        StringBuilder grid = new StringBuilder();
        formatter.printQueryName(grid, name);
        int totalRows = formatter.printGrid(grid, model);

        if (totalRows > 0) {
            this.getOutputWriter().append(grid.toString()).append("\n\n");
            this.getOutputWriter().append(String.valueOf(totalRows)).append(" row(s) fetched.\n\n");
            this.getOutputWriter().flush();
            this.scrollToEnd();
        }
    }

}