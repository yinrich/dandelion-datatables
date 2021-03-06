package com.github.dandelion.datatables.core.extension.feature;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import com.github.dandelion.core.asset.generator.js.JsFunction;
import com.github.dandelion.core.web.AssetRequestContext;
import com.github.dandelion.datatables.core.extension.AbstractExtensionTest;
import com.github.dandelion.datatables.core.extension.Extension;
import com.github.dandelion.datatables.core.option.CallbackType;

import static org.assertj.core.api.Assertions.assertThat;

public class ServerSideFeatureTest extends AbstractExtensionTest {

	@Test
	public void shoud_load_the_extension_with_the_given_ajax_source() {
		
		extensionProcessor.process(new HashSet<Extension>(Arrays.asList(new ServerSideFeature())));

		assertThat(AssetRequestContext.get(table.getTableConfiguration().getRequest()).getBundles(true)).hasSize(0);
		assertThat(mainConfig.get(CallbackType.INIT.getName()).toString())
		.isEqualTo(new JsFunction("oTable_fakeId.columns.adjust().draw();", CallbackType.INIT.getArgs()).toString());
	}
}
