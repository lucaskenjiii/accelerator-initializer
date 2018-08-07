/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.hopper.initializer.zip;

import static java.lang.String.format;
import com.hopper.exception.InitializerException;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class ZipFileImpl implements ZipFile {
    
    private static final String ZIP_FORMAT = "%s.zip";
    
    @Override
    public File zip(String projectPath) {
        String zipPath = format(ZIP_FORMAT, projectPath);
        File fileToZip = new File(projectPath);
        try (FileOutputStream fos = new FileOutputStream(zipPath);
             ZipOutputStream zipOut = new ZipOutputStream(fos);) {
             zipFile(fileToZip, fileToZip.getName(), zipOut);
            return new File(zipPath);
        } catch (IOException e) {
            throw new InitializerException("It was not possible zip path {}",e, projectPath);
        }
    }

    private void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isDirectory()) {
            File[] children = fileToZip.listFiles();
            if(children.length <= 0) {
                zipOut.putNextEntry(new ZipEntry(fileName+"/"));
                return;
            }
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        try (FileInputStream fis = new FileInputStream(fileToZip);) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        } catch (IOException e) {
            throw new InitializerException("It was not possible to zip project", e);
        }
    }
}