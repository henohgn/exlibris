/*
 * Copyright (c) 2017 Haulmont
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ml.exlibris.exlibris.web.components;

import ml.exlibris.exlibris.entity.BookInstance;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import ml.exlibris.exlibris.web.department_assigning.LibraryAssigning;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Action that allows to assign library department to book instances, selected in a table.
 * <p>
 * This action is used by BookInstanceBrowse and AccessionRegisterWindow.
 */
public class AssignLibraryAction extends AbstractAction {

    private Table<BookInstance> booksInstancesTable;

    public AssignLibraryAction(Table<BookInstance> booksInstancesTable) {
        super("assignLibraryDepartment");
        this.booksInstancesTable = booksInstancesTable;
    }

    @Override
    public void actionPerform(Component component) {
        Frame frame = booksInstancesTable.getFrame();

        Set<BookInstance> bookInstances = booksInstancesTable.getSelected();

        if (!bookInstances.isEmpty()) {
            // Parameters passed to DepartmentAssigning window
            Map<String, Object> params = new HashMap<>();
            params.put(LibraryAssigning.INSTANCES_PARAM, bookInstances);
            params.put(LibraryAssigning.VIEW_PARAM, booksInstancesTable.getDatasource().getView());

            final LibraryAssigning departmentAssigningWindow = (LibraryAssigning) frame.openWindow("department-assigning",
                    WindowManager.OpenType.DIALOG.width((float)400), params);
            departmentAssigningWindow.addListener(actionId -> {
                if (LibraryAssigning.SUCCESS_ACTION.equals(actionId)) {
                    for (BookInstance assignedInstance : departmentAssigningWindow.getAssignedInstances()) {
                        // Put returned instances back into datasource
                        booksInstancesTable.getDatasource().updateItem(assignedInstance);
                    }
                }
            });
        } else {
            frame.showNotification(messages.getMainMessage("selectBookInstancesMessage.text"),
                    Frame.NotificationType.HUMANIZED);
        }
    }
}
