package art.pro.FastRename.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.*;

public final class RenameUtil {

    public String test = ""; // FOR TESTING**********
    public String test2 = ""; // FOR TESTING**********

        public void renameToPattern(String directoryPath, String pattern, int startNumber, int incrementBy,
                int digitsNumber, boolean increment, boolean patternLeading, boolean sorted) {

            Path path = Paths.get(directoryPath);
            boolean hasReadAndExecutePermission = Files.isReadable(path) && Files.isExecutable(path);

            if (!hasReadAndExecutePermission) {
                setFullPermissions(directoryPath);
                test += "Changed permission for directory: " + directoryPath; // FOR TESTING**********
            }

            File[] files = new File(directoryPath).listFiles(File::isFile);

            if(files != null) {
                test += "The path is--: " + directoryPath + " ---- The folder path is not null -----"; // FOR TESTING**********
                if(sorted){
                    Arrays.sort(files, Comparator.comparing(File::getName));
                }
                renameFiles(files, directoryPath, pattern, startNumber, digitsNumber, incrementBy, increment, patternLeading);
            } else {
                test += "The path is--: " + directoryPath + " ---- The folder path was null -----"; // FOR TESTING**********
            }

        }

    private void renameFiles(File[] files, String directoryPath, String pattern, int startNumber,
            int digitsNumber, int incrementBy, boolean increment, boolean patternLeading) {

        int serialNumber = startNumber;
        for (File file : files) {
            String extension = file.getName().substring(file.getName().lastIndexOf("."));
            String newFileName = generateNewName(patternLeading, digitsNumber, pattern, serialNumber, extension);
            File newFile = new File(directoryPath + "\\" + newFileName);
            file.renameTo(newFile);
            test2 = "   @@@ Absolute path: " + file.getAbsolutePath() + "   Canonical path: " + file.getAbsolutePath();
            serialNumber += increment ? incrementBy : -incrementBy;
        }
    }

    private String generateNewName(boolean patternLeading, int digitsNumber, String pattern, int serialNumber, String extension) {
        String newFileName;
        if(patternLeading) {
            newFileName = String.format("%s%0" + digitsNumber + "d%s", pattern, serialNumber, extension);
        } else {
            newFileName = String.format("%0" + digitsNumber + "d%s%s", serialNumber, pattern, extension);
        }
        return newFileName;
    }

    private void setFullPermissions(String directoryPath) {
        Path path = Paths.get(directoryPath);
        try {
            Files.setAttribute(path, "dos:readonly", false);
            Files.setAttribute(path, "dos:hidden", false);
            Files.setAttribute(path, "dos:system", false);
            Files.setAttribute(path, "acl:acl", getWindowsFullControlAcl());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AclFileAttributeView getWindowsFullControlAcl() throws IOException {
        AclEntry.Builder builder = AclEntry.newBuilder();
        builder.setType(AclEntryType.ALLOW);
        UserPrincipalLookupService lookupService = FileSystems.getDefault().getUserPrincipalLookupService();
        UserPrincipal everyone = lookupService.lookupPrincipalByName("Everyone");
        builder.setPrincipal(everyone);
        builder.setPermissions(AclEntryPermission.READ_DATA, AclEntryPermission.WRITE_DATA, AclEntryPermission.APPEND_DATA,
                AclEntryPermission.READ_NAMED_ATTRS, AclEntryPermission.WRITE_NAMED_ATTRS,
                AclEntryPermission.READ_ATTRIBUTES, AclEntryPermission.WRITE_ATTRIBUTES,
                AclEntryPermission.DELETE, AclEntryPermission.READ_ACL, AclEntryPermission.WRITE_ACL,
                AclEntryPermission.WRITE_OWNER, AclEntryPermission.SYNCHRONIZE);
        AclEntry aclEntry = builder.build();
        AclEntry[] aclEntries = { aclEntry };
        AclFileAttributeView aclView = Files.getFileAttributeView(Paths.get(""), AclFileAttributeView.class);
        aclView.setAcl(Arrays.asList(aclEntries));
        return aclView;
    }
}
