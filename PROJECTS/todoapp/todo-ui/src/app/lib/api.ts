// Types based on API schema
export type TodoStatus = 'PENDING' | 'IN_PROGRESS' | 'DONE';

export interface Todo {
  id: number;
  title: string;
  description?: string;
  status: TodoStatus;
  createdAt: string;
  updatedAt: string;
}

export interface TodoCreateRequest {
  title: string;
  description?: string;
}

export interface TodoUpdateRequest {
  title: string;
  description?: string;
  status: TodoStatus;
}

export interface StatusUpdateRequest {
  status: TodoStatus;
}

export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  number: number;
  size: number;
  first: boolean;
  last: boolean;
  numberOfElements: number;
  empty: boolean;
}

export interface TodoStats {
  total: number;
  pending: number;
  inProgress: number;
  done: number;
}

// API Service Class
export class TodoApiService {
  private baseUrl: string;

  constructor(baseUrl: string = 'http://localhost:8080/api/todos') {
    this.baseUrl = baseUrl;
  }

  private async handleResponse<T>(response: Response): Promise<T> {
    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`API Error: ${response.status} - ${errorText || response.statusText}`);
    }
    
    // Handle empty responses (like DELETE)
    const contentType = response.headers.get('content-type');
    if (!contentType || !contentType.includes('application/json')) {
      return {} as T;
    }
    
    return response.json();
  }

  /**
   * Get all todos with optional filtering and search
   */
  async getAllTodos(params?: {
    status?: TodoStatus | 'ALL';
    search?: string;
    page?: number;
    size?: number;
  }): Promise<Todo[]> {
    const searchParams = new URLSearchParams();
    
    if (params?.status && params.status !== 'ALL') {
      // Convert frontend status to backend format
      const backendStatus = this.convertToBackendStatus(params.status);
      searchParams.append('status', backendStatus);
    }
    if (params?.search) searchParams.append('search', params.search);
    if (params?.page !== undefined) searchParams.append('page', params.page.toString());
    if (params?.size !== undefined) searchParams.append('size', params.size.toString());

    const response = await fetch(`${this.baseUrl}?${searchParams}`);
    const todos = await this.handleResponse<Todo[]>(response);
    
    // Convert backend status to frontend format
    return todos.map(todo => ({
      ...todo,
      status: this.convertToFrontendStatus(todo.status)
    }));
  }

  /**
   * Get paginated todos with sorting
   */
  async getTodosWithPagination(params?: {
    status?: TodoStatus | 'ALL';
    page?: number;
    size?: number;
    sortBy?: string;
    sortDirection?: 'asc' | 'desc';
  }): Promise<PageResponse<Todo>> {
    const searchParams = new URLSearchParams();
    
    if (params?.status && params.status !== 'ALL') {
      const backendStatus = this.convertToBackendStatus(params.status);
      searchParams.append('status', backendStatus);
    }
    if (params?.page !== undefined) searchParams.append('page', params.page.toString());
    if (params?.size !== undefined) searchParams.append('size', params.size.toString());
    if (params?.sortBy) searchParams.append('sortBy', params.sortBy);
    if (params?.sortDirection) searchParams.append('sortDirection', params.sortDirection);

    const response = await fetch(`${this.baseUrl}/paginated?${searchParams}`);
    const pageResponse = await this.handleResponse<PageResponse<Todo>>(response);
    
    // Convert backend status to frontend format
    return {
      ...pageResponse,
      content: pageResponse.content.map(todo => ({
        ...todo,
        status: this.convertToFrontendStatus(todo.status)
      }))
    };
  }

  /**
   * Get a specific todo by ID
   */
  async getTodoById(id: number): Promise<Todo> {
    const response = await fetch(`${this.baseUrl}/${id}`);
    const todo = await this.handleResponse<Todo>(response);
    
    return {
      ...todo,
      status: this.convertToFrontendStatus(todo.status)
    };
  }

  /**
   * Create a new todo
   */
  async createTodo(todo: TodoCreateRequest): Promise<Todo> {
    const response = await fetch(this.baseUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(todo),
    });
    
    const createdTodo = await this.handleResponse<Todo>(response);
    return {
      ...createdTodo,
      status: this.convertToFrontendStatus(createdTodo.status)
    };
  }

  /**
   * Update an existing todo
   */
  async updateTodo(id: number, todo: TodoUpdateRequest): Promise<Todo> {
    const todoWithBackendStatus = {
      ...todo,
      status: this.convertToBackendStatus(todo.status)
    };

    const response = await fetch(`${this.baseUrl}/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(todoWithBackendStatus),
    });
    
    const updatedTodo = await this.handleResponse<Todo>(response);
    return {
      ...updatedTodo,
      status: this.convertToFrontendStatus(updatedTodo.status)
    };
  }

  /**
   * Update only the status of a todo
   */
  async updateTodoStatus(id: number, statusUpdate: StatusUpdateRequest): Promise<Todo> {
    const backendStatusUpdate = {
      status: this.convertToBackendStatus(statusUpdate.status)
    };

    const response = await fetch(`${this.baseUrl}/${id}/status`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(backendStatusUpdate),
    });
    
    const updatedTodo = await this.handleResponse<Todo>(response);
    return {
      ...updatedTodo,
      status: this.convertToFrontendStatus(updatedTodo.status)
    };
  }

  /**
   * Delete a todo
   */
  async deleteTodo(id: number): Promise<void> {
    const response = await fetch(`${this.baseUrl}/${id}`, {
      method: 'DELETE',
    });
    
    await this.handleResponse<void>(response);
  }

  /**
   * Get todo statistics
   */
  async getTodoStats(): Promise<Record<string, any>> {
    const response = await fetch(`${this.baseUrl}/stats`);
    return this.handleResponse<Record<string, any>>(response);
  }

  /**
   * Health check endpoint
   */
  async healthCheck(): Promise<string> {
    const response = await fetch('http://localhost:8080/');
    return this.handleResponse<string>(response);
  }

  /**
   * Convert frontend status format to backend format
   */
  private convertToBackendStatus(frontendStatus: TodoStatus): string {
    const statusMap: Record<TodoStatus, string> = {
      'PENDING': 'Pending',
      'IN_PROGRESS': 'In Progress',
      'DONE': 'Done'
    };
    return statusMap[frontendStatus];
  }

  /**
   * Convert backend status format to frontend format
   */
  private convertToFrontendStatus(backendStatus: any): TodoStatus {
    const statusMap: Record<string, TodoStatus> = {
      'Pending': 'PENDING',
      'In Progress': 'IN_PROGRESS',
      'Done': 'DONE'
    };
    return statusMap[backendStatus] || 'PENDING';
  }
}

// Create a default instance
export const todoApi = new TodoApiService();

// Utility functions for status handling
export const getStatusDisplay = (status: TodoStatus): string => {
  const displayMap: Record<TodoStatus, string> = {
    'PENDING': 'Pending',
    'IN_PROGRESS': 'In Progress',
    'DONE': 'Done'
  };
  return displayMap[status];
};

export const getStatusColor = (status: TodoStatus): string => {
  const colorMap: Record<TodoStatus, string> = {
    'PENDING': 'bg-orange-500',
    'IN_PROGRESS': 'bg-blue-500',
    'DONE': 'bg-green-500'
  };
  return colorMap[status];
};

export const getStatusBg = (status: TodoStatus): string => {
  const bgMap: Record<TodoStatus, string> = {
    'PENDING': 'bg-orange-100 border-orange-200',
    'IN_PROGRESS': 'bg-blue-100 border-blue-200',
    'DONE': 'bg-green-100 border-green-200'
  };
  return bgMap[status];
};