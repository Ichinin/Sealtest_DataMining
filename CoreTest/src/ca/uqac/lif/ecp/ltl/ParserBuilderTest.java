/*
    Log trace triaging and etc.
    Copyright (C) 2016 Sylvain Hallé

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.uqac.lif.ecp.ltl;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.ecp.atomic.AtomicEvent;
import ca.uqac.lif.ecp.ltl.OperatorBuilder.BuildException;

public class ParserBuilderTest
{
	@Test
	public void test1() throws BuildException
	{
		String expression = "p";
		AtomicParserBuilder apb = new AtomicParserBuilder(expression);
		Operator<AtomicEvent> op = apb.build();
		assertNotNull(op);
		assertTrue(op instanceof Atom);
	}
	
	@Test
	public void test2() throws BuildException
	{
		String expression = "p & q";
		AtomicParserBuilder apb = new AtomicParserBuilder(expression);
		Operator<AtomicEvent> op = apb.build();
		assertNotNull(op);
		assertTrue(op instanceof And);
	}
	
	@Test
	public void test3() throws BuildException
	{
		String expression = "G (p & (!q))";
		AtomicParserBuilder apb = new AtomicParserBuilder(expression);
		Operator<AtomicEvent> op = apb.build();
		assertNotNull(op);
		assertTrue(op instanceof Globally);
		assertEquals(5, op.size());
	}
	
	@Test
	public void test4() throws BuildException
	{
		String expression = "a | b";
		AtomicParserBuilder apb = new AtomicParserBuilder(expression);
		Operator<AtomicEvent> op = apb.build();
		assertNotNull(op);
		assertTrue(op instanceof Or);
	}
}
