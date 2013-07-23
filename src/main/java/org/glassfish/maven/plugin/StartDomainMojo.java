/*
 * (c) Copyright 2013 - The gf-maven-plugin developers. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or license/LICENSE.html.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at license/LICENSE.html.
 * This file is subject to the "Classpath" exception as provided by the 
 * copyright holder in the GPL Version 2 section of the
 * License file that accompanied this code.  If applicable, add the following
 * below the License Header, with the fields enclosed by brackets [] replaced
 * by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 */
package org.glassfish.maven.plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Starts a GlassFish domain
 * 
 * @goal start-domain
 * @requiresProject false
 */
public class StartDomainMojo extends GlassFishMojo {

    /**
     * @parameter
     */
    protected String glassfishDirectory;
    /**
     * @parameter
     */
    protected String domainDirectory;
    /**
     * @parameter
     */
    protected Map domain;
    
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        
        try {
            /* Gather options */
            if (glassfishDirectory == null)
                glassfishDirectory = Defaults.glassfishDirectory;
            if (domainDirectory == null)
                domainDirectory = Defaults.domainDirectory; 
            if (domain == null)
                domain = Defaults.domain;
            if (domain.get("name") == null)
                domain.put("name", Defaults.domain.get("name"));
            
            List<String> options = new ArrayList<String>();
            options.add("start-domain");
            options.add("--domaindir");
            options.add(domainDirectory);
            options.add(domain.get("name").toString());
            
            /* Start the domain */
            run(glassfishDirectory, GlassFishMojo.Command.ASADMIN, options);
        } catch (IOException ex) {
            throw new MojoExecutionException(ex.toString());
        }
    }
}
