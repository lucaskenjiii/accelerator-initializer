/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.hopper.initializer.creator;

public interface FileCreator<T> {
    
    default int order() {
        return 0;
    }
    
    void create(T request);
    
}