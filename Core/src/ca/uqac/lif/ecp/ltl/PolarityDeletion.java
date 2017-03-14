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

import ca.uqac.lif.ecp.Event;

/**
 * Hologram transformation that deletes all the children of a node that
 * do not determine its truth value. This is a refinement over
 * {@link FailFastDeletion}.
 * 
 * @author Sylvain Hallé
 *
 * @param <T> The event type
 */
public class PolarityDeletion<T extends Event> extends FailFastDeletion<T> 
{
	@Override
	public Operator<T> transform(Operator<T> tree) 
	{
		transformRecursive(tree, false);
		return tree;
	}
	
	@Override
	public String toString()
	{
		return "Polarity deletion";
	}
}
