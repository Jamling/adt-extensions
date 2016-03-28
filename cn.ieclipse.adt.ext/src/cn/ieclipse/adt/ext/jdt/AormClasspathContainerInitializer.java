/*
 * Copyright 2010 Android ORM projects.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ieclipse.adt.ext.jdt;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.osgi.framework.Bundle;

import cn.ieclipse.adt.ext.AormPlugin;

/**
 * @author Jamling
 *         
 */
public class AormClasspathContainerInitializer
        extends ClasspathContainerInitializer {
    private static final String RESOURCE_LIB = "libs";
    private static final String FS = System.getProperty("file.separator");
    private static final String AORM_NAME = "aorm-core-1.0.jar";
    private static final String AORM_NAME_SOURCE = "aorm-core-1.0-sources.jar";

    public static final IClasspathEntry getContainerEntry() {
        return JavaCore
                .newContainerEntry(new Path(AormClasspathContainer.CON_PATH));
    }

    @Override
    public void initialize(IPath containerPath, IJavaProject project)
            throws CoreException {
        if (AormClasspathContainer.CON_PATH.equals(containerPath.toString())) {
            IClasspathContainer container = allocateAndroidContainer(project);
            if (container != null)
                JavaCore.setClasspathContainer(
                        new Path(AormClasspathContainer.CON_PATH),
                        new IJavaProject[] { project },
                        new IClasspathContainer[] { container },
                        new NullProgressMonitor());
        }

    }

    private IClasspathContainer allocateAndroidContainer(IJavaProject project) {
        IClasspathContainer ormContainer = new AormClasspathContainer(
                getClasspathEntries(),
                new Path(AormClasspathContainer.CON_PATH));
        return ormContainer;
    }

    public static boolean invalidOrmClassPath(IPath path) {
        boolean ret = false;
        String file = path.lastSegment();
        if (file.startsWith("aorm")) {
            Bundle bundle = AormPlugin.getDefault().getBundle();
            Enumeration<URL> urls = bundle.findEntries(RESOURCE_LIB, file,
                    false);
            ret = urls == null || !urls.hasMoreElements();
        }
        return ret;
    }

    public static IClasspathEntry[] getClasspathEntries() {
        ArrayList<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
        Bundle bundle = AormPlugin.getDefault().getBundle();
        Enumeration<URL> urls = bundle.findEntries(RESOURCE_LIB, "*.jar",
                false);
        ArrayList<Path> paths = new ArrayList<Path>();
        if (urls != null) {
            while (urls.hasMoreElements()) {
                URL url = (URL) urls.nextElement();
                try {
                    url = FileLocator.resolve(url);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Path path = new Path(url.getPath());
                paths.add(path);
            }
        }
        
        Path libPath = getLibPath(AORM_NAME, paths);
        if (libPath != null) {
            // Path docPath = getJarPath(AORM_NAME + ".doc", paths);
            Path sourcePath = getLibPath(AORM_NAME_SOURCE, paths);
            IClasspathEntry entry = JavaCore.newLibraryEntry(libPath,
                    sourcePath, null, null, null, true);                    
            entries.add(entry);
        }
        return entries.toArray(new IClasspathEntry[entries.size()]);
    }
    
    @Deprecated
    private static Path getJarPath(String name, ArrayList<Path> paths) {
        Path ret = null;
        String v = null;
        for (Path path : paths) {
            String fn = path.lastSegment();
            if (fn != null) {
                String ver = fn.replace(AORM_NAME, "").replace(".jar", "");
                if (ver.matches("[\\d\\.]+")) { // is lib
                    if (v != null) {
                        try {
                            if (compareVersion(v, ver) < 0) {
                                v = ver;
                                ret = path;
                            }
                        } catch (Exception e) {
                        }
                    }
                    else {
                        v = ver;
                        ret = path;
                    }
                }
            }
        }
        return ret;
    }
    
    private static Path getLibPath(String name, ArrayList<Path> paths) {
        for (Path path : paths) {
            String fn = path.lastSegment();
            if (fn != null) {
                if (fn.equals(name)) {
                    return path;
                }
            }
        }
        return null;
    }
    
    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * 
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2)
            throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");// 注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);// 取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length()
                        - versionArray2[idx].length()) == 0// 先比较长度
                && (diff = versionArray1[idx]
                        .compareTo(versionArray2[idx])) == 0) {// 再比较字符
            ++idx;
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }
}

