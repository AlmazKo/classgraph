/*
 * This file is part of ClassGraph.
 *
 * Author: Luke Hutchison
 *
 * Hosted at: https://github.com/classgraph/classgraph
 *
 * --
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Luke Hutchison
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package nonapi.io.github.classgraph.classloaderhandler;

import nonapi.io.github.classgraph.ScanSpec;
import nonapi.io.github.classgraph.classpath.ClasspathOrder;
import nonapi.io.github.classgraph.utils.LogNode;

/**
 * A placeloader ClassLoaderHandler that matches Java 9+ classloaders, but does not attempt to extract URLs from
 * them (module scanning uses a different mechanism from classpath scanning).
 */
public class JPMSClassLoaderHandler implements ClassLoaderHandler {

    /* (non-Javadoc)
     * @see nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandler#handledClassLoaders()
     */
    @Override
    public String[] handledClassLoaders() {
        return new String[] { //
                "jdk.internal.loader.ClassLoaders$AppClassLoader", //
                "jdk.internal.loader.BuiltinClassLoader" };
    }

    /* (non-Javadoc)
     * @see nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandler#getEmbeddedClassLoader(java.lang.ClassLoader)
     */
    @Override
    public ClassLoader getEmbeddedClassLoader(final ClassLoader outerClassLoaderInstance) {
        return null;
    }

    /* (non-Javadoc)
     * @see nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandler#getDelegationOrder(java.lang.ClassLoader)
     */
    @Override
    public DelegationOrder getDelegationOrder(final ClassLoader classLoaderInstance) {
        return DelegationOrder.PARENT_FIRST;
    }

    /* (non-Javadoc)
     * @see nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandler#handle(nonapi.io.github.classgraph.ScanSpec, java.lang.ClassLoader, nonapi.io.github.classgraph.classpath.ClasspathOrder, nonapi.io.github.classgraph.utils.LogNode)
     */
    @Override
    public void handle(final ScanSpec scanSpec, final ClassLoader classLoader,
            final ClasspathOrder classpathOrderOut, final LogNode log) {
        // The JDK9 classloaders have a field, URLClassPath ucp, containing URLs for unnamed modules,
        // but it is not visible.
    }
}
