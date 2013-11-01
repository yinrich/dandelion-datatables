/*
 * [The "BSD licence"]
 * Copyright (c) 2012 Dandelion
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. Neither the name of Dandelion nor the names of its contributors 
 * may be used to endorse or promote products derived from this software 
 * without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.dandelion.datatables.core.extension.feature;

import com.github.dandelion.datatables.core.exception.ExtensionLoadingException;
import com.github.dandelion.datatables.core.extension.AbstractExtension;
import com.github.dandelion.datatables.core.generator.configuration.ColumnFilteringGenerator;
import com.github.dandelion.datatables.core.html.HtmlTable;

/**
 * Java implementation of the DataTables Column Filter Add-on written by Jovan Popovic.
 *
 * @see http://code.google.com/p/jquery-datatables-column-filter/
 * @author Thibault Duchateau
 * @since 0.7.1
 */
public abstract class AbstractFilteringFeature extends AbstractExtension {

	@Override
	public String getName() {
		return "Filtering";
	}

	@Override
	public void setup(HtmlTable table) throws ExtensionLoadingException {

		if(table.getTableConfiguration().getFeatureFilterPlaceholder() != null){
			switch (table.getTableConfiguration().getFeatureFilterPlaceholder()){
			case FOOT:
				adaptFooter(table);
				break;
			case HEAD_AFTER:
				adaptHeader(table);
				break;
			case HEAD_BEFORE:
				adaptHeader(table);
				break;
			case NONE:
				break;
			}
		}
		// Default: footer
		else{
			adaptFooter(table);
		}
		
		setFunction("columnFilter");
		setConfigGenerator(new ColumnFilteringGenerator());
		
		addScope("filtering");
	}
	
	protected abstract void adaptHeader(HtmlTable table);
	protected abstract void adaptFooter(HtmlTable table);
}