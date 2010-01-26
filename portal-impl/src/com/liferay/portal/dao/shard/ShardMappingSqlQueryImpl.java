/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.dao.shard;

import com.liferay.portal.dao.jdbc.spring.MappingSqlQueryImpl;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;

import java.util.List;

import javax.sql.DataSource;

/**
 * <a href="ShardMappingSqlQueryImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Alexander Chow
 */
public class ShardMappingSqlQueryImpl<T> extends MappingSqlQueryImpl<T> {

	public ShardMappingSqlQueryImpl(
		DataSource dataSource, String sql, int[] types,
		RowMapper<T> rowMapper) {

		super(dataSource, sql, types, rowMapper);
	}

	public List<T> execute(Object... params) {
		setDataSource(ShardUtil.getDataSource());

		return super.execute(params);
	}

}