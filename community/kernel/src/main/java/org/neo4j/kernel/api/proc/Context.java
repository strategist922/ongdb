/*
 * Copyright (c) 2018-2020 "Graph Foundation"
 * Graph Foundation, Inc. [https://graphfoundation.org]
 *
 * Copyright (c) 2002-2020 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of ONgDB.
 *
 * ONgDB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.api.proc;

import java.time.Clock;

import org.neo4j.graphdb.DependencyResolver;
import org.neo4j.internal.kernel.api.exceptions.ProcedureException;
import org.neo4j.internal.kernel.api.procs.ProcedureCallContext;
import org.neo4j.internal.kernel.api.security.SecurityContext;
import org.neo4j.kernel.api.KernelTransaction;
import org.neo4j.kernel.internal.GraphDatabaseAPI;

/**
 * The context in which a procedure is invoked. This is a read-only map-like structure.
 * For instance, a read-only transactional procedure might have access to the current statement it is being invoked
 * in through this.
 *
 * The context is entirely defined by the caller of the procedure,
 * so what is available in the context depends on the context of the call.
 */
public interface Context
{
    Key<DependencyResolver> DEPENDENCY_RESOLVER = Key.key( "DependencyResolver", DependencyResolver.class );
    Key<GraphDatabaseAPI> DATABASE_API = Key.key( "DatabaseAPI", GraphDatabaseAPI.class );
    Key<KernelTransaction> KERNEL_TRANSACTION = Key.key( "KernelTransaction", KernelTransaction.class );
    Key<SecurityContext> SECURITY_CONTEXT = Key.key( "SecurityContext", SecurityContext.class );
    Key<ProcedureCallContext> PROCEDURE_CALL_CONTEXT = Key.key( "ProcedureCallContext", ProcedureCallContext.class );
    Key<Thread> THREAD = Key.key( "Thread", Thread.class );
    Key<Clock> SYSTEM_CLOCK = Key.key( "SystemClock", Clock.class );
    Key<Clock> STATEMENT_CLOCK = Key.key( "StatementClock", Clock.class );
    Key<Clock> TRANSACTION_CLOCK = Key.key( "TransactionClock", Clock.class );

    <T> T get( Key<T> key ) throws ProcedureException;
    <T> T getOrElse( Key<T> key, T orElse );
}
