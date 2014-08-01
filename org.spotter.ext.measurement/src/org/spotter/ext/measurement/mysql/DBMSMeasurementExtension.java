/**
 * Copyright 2014 SAP AG
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
package org.spotter.ext.measurement.mysql;

import org.lpe.common.config.ConfigParameterDescription;
import org.lpe.common.util.LpeSupportedTypes;
import org.spotter.core.measurement.AbstractMeasurmentExtension;
import org.spotter.core.measurement.IMeasurementController;

/**
 * Extension for measurement / sampling of a DBMS.
 * 
 * @author Alexander Wert
 * 
 */

public class DBMSMeasurementExtension extends AbstractMeasurmentExtension {

	private static final String EXTENSION_DESCRIPTION = "The DBMS sampling measurement satellite adapter is used to connect "
														+ "to a MySQL DBMS and to query the database status.";
	
	@Override
	public String getName() {
		return "measurement.satellite.adapter.sampling.dbms";
	}
	
	@Override
	protected String getDefaultSatelleiteExtensionName() {
		return "DBMS Sampling Measurement Satellite Adapter";
	}

	@Override
	public IMeasurementController createExtensionArtifact() {
		return new DBMSMeasurement(this);
	}

	private ConfigParameterDescription createSamplingDelayParameter() {
		ConfigParameterDescription samplingDelayParameter = new ConfigParameterDescription(
				DBMSMeasurement.SAMPLING_DELAY, LpeSupportedTypes.Long);
		samplingDelayParameter.setDefaultValue(String.valueOf(DBMSMeasurement.DEFAULT_DELAY));
		samplingDelayParameter.setDescription("The sampling interval in milliseconds.");

		return samplingDelayParameter;
	}

	private ConfigParameterDescription createConnectionStringParameter() {
		ConfigParameterDescription samplingDelayParameter = new ConfigParameterDescription(
				DBMSMeasurement.CONNECTION_STRING, LpeSupportedTypes.String);
		samplingDelayParameter.setMandatory(true);
		samplingDelayParameter.setDescription("The connection string to the database");

		return samplingDelayParameter;
	}

	private ConfigParameterDescription createCollectorTypeParameter() {
		ConfigParameterDescription collectorTypeParameter = new ConfigParameterDescription(
				DBMSMeasurement.COLLECTOR_TYPE_KEY, LpeSupportedTypes.String);
		collectorTypeParameter.setMandatory(true);
		collectorTypeParameter.setDescription("Type to use for data collector");

		return collectorTypeParameter;
	}

	@Override
	protected void initializeConfigurationParameters() {
		addConfigParameter(createSamplingDelayParameter());
		addConfigParameter(createConnectionStringParameter());
		addConfigParameter(createCollectorTypeParameter());
		addConfigParameter(ConfigParameterDescription.createExtensionDescription(EXTENSION_DESCRIPTION));
	}

	@Override
	public boolean testConnection(String host, String port) {
		return true;
	}

	@Override
	public boolean isRemoteExtension() {
		return false;
	}

}
