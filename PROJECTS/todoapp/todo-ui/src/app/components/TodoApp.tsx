'use client';

import React, { useState, useEffect, useCallback } from 'react';
import { Search, User, Bell, Trash2, Check, Clock, Play, MoreVertical, Plus, Loader2, AlertCircle } from 'lucide-react';
import { 
  todoApi, 
  Todo, 
  TodoCreateRequest, 
  TodoStatus, 
  TodoStats,
  getStatusDisplay,
  getStatusColor,
  getStatusBg
} from '@/lib/api';

const TodoApp: React.FC = () => {
  const [todos, setTodos] = useState<Todo[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [statusFilter, setStatusFilter] = useState<TodoStatus | 'ALL'>('ALL');
  const [swipedCard, setSwipedCard] = useState<number | null>(null);
  const [showCreateModal, setShowCreateModal] = useState(false);
  const [newTodo, setNewTodo] = useState<TodoCreateRequest>({ title: '', description: '' });
  const [currentPage, setCurrentPage] = useState(0);
  const [pageSize] = useState(20);
  const [stats, setStats] = useState<TodoStats>({ total: 0, pending: 0, inProgress: 0, done: 0 });

  const fetchTodos = useCallback(async () => {
    try {
      setLoading(true);
      setError(null);
      const fetchedTodos = await todoApi.getAllTodos({
        status: statusFilter,
        search: searchTerm || undefined,
        page: currentPage,
        size: pageSize,
      });
      setTodos(fetchedTodos);
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to fetch todos');
      console.error('Error fetching todos:', err);
    } finally {
      setLoading(false);
    }
  }, [statusFilter, searchTerm, currentPage, pageSize]);

  const fetchStats = useCallback(async () => {
    try {
      // Calculate stats from current todos
      const calculatedStats: TodoStats = {
        total: todos.length,
        pending: todos.filter(t => t.status === 'PENDING').length,
        inProgress: todos.filter(t => t.status === 'IN_PROGRESS').length,
        done: todos.filter(t => t.status === 'DONE').length
      };
      setStats(calculatedStats);

      // Optionally fetch from API if backend provides stats
      try {
        await todoApi.getTodoStats();
        // If API stats are different, you can use them here
      } catch (apiError) {
        // Fallback to calculated stats if API fails
        console.warn('Failed to fetch API stats, using calculated stats');
      }
    } catch (err) {
      console.error('Failed to calculate stats:', err);
    }
  }, [todos]);

  useEffect(() => {
    fetchTodos();
  }, [fetchTodos]);

  useEffect(() => {
    fetchStats();
  }, [fetchStats]);

  // Debounce search
  useEffect(() => {
    const timeoutId = setTimeout(() => {
      if (searchTerm !== undefined) {
        fetchTodos();
      }
    }, 300);
    return () => clearTimeout(timeoutId);
  }, [searchTerm]);

  const handleCreateTodo = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!newTodo.title.trim()) return;

    try {
      await todoApi.createTodo(newTodo);
      setNewTodo({ title: '', description: '' });
      setShowCreateModal(false);
      await fetchTodos();
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to create todo');
    }
  };

  const updateTodoStatus = async (id: number, newStatus: TodoStatus) => {
    try {
      await todoApi.updateTodoStatus(id, { status: newStatus });
      setSwipedCard(null);
      await fetchTodos();
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to update todo status');
    }
  };

  const deleteTodo = async (id: number) => {
    if (!confirm('Are you sure you want to delete this todo?')) return;
    
    try {
      await todoApi.deleteTodo(id);
      setSwipedCard(null);
      await fetchTodos();
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to delete todo');
    }
  };

  const getStatusIcon = (status: TodoStatus) => {
    switch (status) {
      case 'PENDING': return <Clock className="w-4 h-4" />;
      case 'IN_PROGRESS': return <Play className="w-4 h-4" />;
      case 'DONE': return <Check className="w-4 h-4" />;
    }
  };

  const getNextStatus = (currentStatus: TodoStatus): TodoStatus | null => {
    switch (currentStatus) {
      case 'PENDING': return 'IN_PROGRESS';
      case 'IN_PROGRESS': return 'DONE';
      case 'DONE': return null;
    }
  };

  const getNextStatusLabel = (currentStatus: TodoStatus): string => {
    const nextStatus = getNextStatus(currentStatus);
    if (!nextStatus) return '';
    
    switch (nextStatus) {
      case 'IN_PROGRESS': return 'Start Progress';
      case 'DONE': return 'Mark Done';
      default: return '';
    }
  };

  if (loading && todos.length === 0) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <Loader2 className="w-8 h-8 animate-spin text-blue-500 mx-auto mb-4" />
          <p className="text-gray-600">Loading todos...</p>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 p-4 md:p-6">
      <div className="max-w-6xl mx-auto">
        {/* Header */}
        <div className="flex justify-between items-center mb-8">
          <h1 className="text-3xl font-bold text-gray-900">To-do List</h1>
          <div className="flex items-center space-x-4">
            <div className="relative">
              <User className="w-6 h-6 text-gray-600" />
              <div className="absolute -top-1 -right-1 w-3 h-3 bg-orange-500 rounded-full"></div>
            </div>
            <div className="relative">
              <Bell className="w-6 h-6 text-gray-600" />
              <div className="absolute -top-1 -right-1 w-3 h-3 bg-red-500 rounded-full flex items-center justify-center">
                <span className="text-white text-xs">1</span>
              </div>
            </div>
          </div>
        </div>

        {/* Error Display */}
        {error && (
          <div className="bg-red-50 border border-red-200 rounded-xl p-4 mb-6 flex items-center space-x-2">
            <AlertCircle className="w-5 h-5 text-red-500 flex-shrink-0" />
            <span className="text-red-700 flex-1">{error}</span>
            <button
              onClick={() => setError(null)}
              className="text-red-500 hover:text-red-700 text-xl leading-none"
            >
              ×
            </button>
          </div>
        )}

        {/* Search Bar */}
        <div className="relative mb-6">
          <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 w-5 h-5 text-gray-400" />
          <input
            type="text"
            placeholder="Search todos..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="w-full pl-10 pr-4 py-3 bg-white border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
          />
          {loading && (
            <Loader2 className="absolute right-3 top-1/2 transform -translate-y-1/2 w-5 h-5 animate-spin text-gray-400" />
          )}
        </div>

        {/* Filter Tabs */}
        <div className="flex space-x-2 mb-6 overflow-x-auto">
          {(['ALL', 'PENDING', 'IN_PROGRESS', 'DONE'] as const).map((filter) => (
            <button
              key={filter}
              onClick={() => setStatusFilter(filter)}
              className={`px-4 py-2 rounded-lg whitespace-nowrap transition-colors ${
                statusFilter === filter
                  ? filter === 'DONE' 
                    ? 'bg-green-500 text-white'
                    : 'bg-gray-900 text-white'
                  : 'bg-white text-gray-600 border border-gray-200 hover:bg-gray-50'
              }`}
            >
              {filter === 'ALL' ? 'All' : getStatusDisplay(filter)}
            </button>
          ))}
        </div>

        {/* Quick Stats */}
        <div className="bg-white rounded-2xl p-6 mb-8">
          <h2 className="text-lg font-semibold text-gray-900 mb-4">Quick Stats</h2>
          <div className="flex justify-end space-x-8">
            <div className="text-center">
              <div className="text-2xl font-bold text-gray-900">{stats.total}</div>
              <div className="text-sm text-gray-500">Total Tasks</div>
            </div>
            <div className="text-center">
              <div className="text-2xl font-bold text-gray-900">{stats.pending + stats.inProgress}</div>
              <div className="text-sm text-gray-500">Active</div>
            </div>
            <div className="text-center">
              <div className="text-2xl font-bold text-gray-900">{stats.done}</div>
              <div className="text-sm text-gray-500">Completed</div>
            </div>
          </div>
          <div className="mt-4 h-2 bg-gray-200 rounded-full overflow-hidden">
            <div 
              className="h-full bg-blue-500 transition-all duration-300" 
              style={{ width: `${stats.total > 0 ? (stats.done / stats.total) * 100 : 0}%` }}
            ></div>
          </div>
        </div>

        {/* Todo Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {todos.map((todo) => (
            <div key={todo.id} className="relative group">
              <div 
                className={`bg-white rounded-2xl p-6 shadow-sm border-2 transition-all duration-200 hover:shadow-md ${getStatusBg(todo.status)} ${
                  swipedCard === todo.id ? 'transform translate-x-2' : ''
                }`}
                onMouseEnter={() => setSwipedCard(todo.id)}
                onMouseLeave={() => setSwipedCard(null)}
              >
                {/* Header with title and menu */}
                <div className="flex items-start justify-between mb-4">
                  <div className="flex-1">
                    <div className="text-sm text-gray-500 mb-1">To-Do</div>
                    <h3 className="font-bold text-gray-900 text-lg leading-tight">{todo.title}</h3>
                  </div>
                  <button className="text-gray-400 hover:text-gray-600 p-1">
                    <MoreVertical className="w-4 h-4" />
                  </button>
                </div>
                
                {/* Description */}
                <p className="text-gray-600 text-sm mb-6 leading-relaxed">
                  {todo.description || "Your description enceteret algoht us description."}
                </p>

                {/* Status Badge and Actions */}
                <div className="flex items-center justify-between">
                  {/* Status Badge */}
                  <div className={`inline-flex items-center space-x-2 px-4 py-2 rounded-xl text-white font-medium text-sm ${getStatusColor(todo.status)}`}>
                    {getStatusIcon(todo.status)}
                    <span>{getStatusDisplay(todo.status)}</span>
                  </div>
                  
                  {/* Quick Action Button */}
                  <div className="flex items-center space-x-2">
                    {todo.status !== 'DONE' && (
                      <button 
                        onClick={() => {
                          const nextStatus = getNextStatus(todo.status);
                          if (nextStatus) {
                            updateTodoStatus(todo.id, nextStatus);
                          }
                        }}
                        className="w-10 h-10 rounded-full bg-gray-100 hover:bg-gray-200 flex items-center justify-center transition-colors"
                        title={getNextStatusLabel(todo.status)}
                      >
                        <div className="w-6 h-6 rounded-full border-2 border-gray-400 flex items-center justify-center">
                          <div className="w-2 h-2 rounded-full bg-gray-400"></div>
                        </div>
                      </button>
                    )}
                    
                    {todo.status === 'DONE' && (
                      <button className="w-10 h-10 rounded-full bg-green-100 flex items-center justify-center">
                        <div className="w-6 h-6 rounded-full bg-green-500 flex items-center justify-center">
                          <Check className="w-3 h-3 text-white" />
                        </div>
                      </button>
                    )}
                  </div>
                </div>

                {/* Hover Actions */}
                <div className={`mt-4 transition-all duration-200 ${swipedCard === todo.id ? 'opacity-100 max-h-20' : 'opacity-0 max-h-0 overflow-hidden'}`}>
                  <div className="flex items-center space-x-3 pt-2 border-t border-gray-100">
                    {/* Swipe/Mark as Done */}
                    <div className="flex items-center space-x-2 text-sm text-gray-600">
                      <Check className="w-4 h-4 text-green-500" />
                      <span>Swipe as Done</span>
                    </div>
                    
                    {/* Delete */}
                    <button
                      onClick={() => deleteTodo(todo.id)}
                      className="flex items-center space-x-2 text-sm text-red-500 hover:text-red-700 transition-colors ml-auto"
                    >
                      <Trash2 className="w-4 h-4" />
                      <span>Delete</span>
                    </button>
                  </div>
                </div>
              </div>

              {/* Mobile Swipe Action - appears on right */}
              {swipedCard === todo.id && (
                <div className="absolute right-2 top-1/2 transform -translate-y-1/2 opacity-0 group-hover:opacity-100 transition-opacity">
                  <button
                    onClick={() => deleteTodo(todo.id)}
                    className="w-12 h-12 bg-red-500 text-white rounded-xl flex items-center justify-center hover:bg-red-600 transition-colors shadow-lg"
                    title="Delete todo"
                  >
                    <Trash2 className="w-5 h-5" />
                  </button>
                </div>
              )}
            </div>
          ))}
        </div>

        {/* Create Todo Button */}
        <div className="fixed bottom-6 right-6">
          <button 
            onClick={() => setShowCreateModal(true)}
            className="w-14 h-14 bg-blue-500 text-white rounded-full shadow-lg flex items-center justify-center hover:bg-blue-600 transition-colors"
            title="Create new todo"
          >
            <Plus className="w-6 h-6" />
          </button>
        </div>

        {/* Empty State */}
        {!loading && todos.length === 0 && (
          <div className="text-center py-12">
            <div className="w-24 h-24 bg-gray-200 rounded-full mx-auto mb-4 flex items-center justify-center">
              <Search className="w-8 h-8 text-gray-400" />
            </div>
            <h3 className="text-lg font-medium text-gray-900 mb-2">No todos found</h3>
            <p className="text-gray-500">
              {searchTerm || statusFilter !== 'ALL' 
                ? 'Try adjusting your search or filter criteria' 
                : 'Create your first todo to get started!'
              }
            </p>
            {(!searchTerm && statusFilter === 'ALL') && (
              <button
                onClick={() => setShowCreateModal(true)}
                className="mt-4 px-6 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition-colors"
              >
                Create Your First Todo
              </button>
            )}
          </div>
        )}

        {/* Create Todo Modal */}
        {showCreateModal && (
          <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
            <div className="bg-white rounded-2xl p-6 w-full max-w-md">
              <h2 className="text-xl font-semibold text-gray-900 mb-4">Create New Todo</h2>
              <form onSubmit={handleCreateTodo}>
                <div className="mb-4">
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Title <span className="text-red-500">*</span>
                  </label>
                  <input
                    type="text"
                    value={newTodo.title}
                    onChange={(e) => setNewTodo({ ...newTodo, title: e.target.value })}
                    placeholder="Enter todo title..."
                    maxLength={200}
                    className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    required
                    autoFocus
                  />
                  <div className="text-xs text-gray-500 mt-1">
                    {newTodo.title.length}/200 characters
                  </div>
                </div>
                
                <div className="mb-6">
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Description
                  </label>
                  <textarea
                    value={newTodo.description || ''}
                    onChange={(e) => setNewTodo({ ...newTodo, description: e.target.value })}
                    placeholder="Enter description (optional)..."
                    maxLength={1000}
                    rows={3}
                    className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                  />
                  <div className="text-xs text-gray-500 mt-1">
                    {(newTodo.description || '').length}/1000 characters
                  </div>
                </div>
                
                <div className="flex space-x-3">
                  <button
                    type="button"
                    onClick={() => {
                      setShowCreateModal(false);
                      setNewTodo({ title: '', description: '' });
                    }}
                    className="flex-1 px-4 py-2 text-gray-700 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors"
                  >
                    Cancel
                  </button>
                  <button
                    type="submit"
                    disabled={!newTodo.title.trim()}
                    className="flex-1 px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 disabled:bg-gray-300 disabled:cursor-not-allowed transition-colors"
                  >
                    Create Todo
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default TodoApp;