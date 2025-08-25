# Todo List Next.js Application

A modern, responsive todo list application built with Next.js 14, TypeScript, and Tailwind CSS.

## Features

- ✅ Create, read, update, and delete todos
- 🔍 Search todos by title and description
- 🏷️ Filter todos by status (Pending, In Progress, Done)
- 📱 Responsive design matching the provided UI mockup
- 🎨 Modern UI with Tailwind CSS
- 🔄 Real-time status updates
- 📝 Modal dialogs for creating and editing todos
- 🚀 Built with Next.js 14 App Router

## Getting Started

### Prerequisites

- Node.js 18+ installed
- Your backend API running on `http://localhost:8080`

### Installation

1. Clone or create the project directory
2. Install dependencies:

```bash
npm install
```

3. Run the development server:

```bash
npm run dev
```

4. Open [http://localhost:3000](http://localhost:3000) in your browser

### Build for Production

```bash
npm run build
npm start
```

## API Integration

The application integrates with your OpenAPI specification and includes:

- Complete CRUD operations for todos
- Search and filtering capabilities
- Pagination support
- Status management
- Error handling

## Project Structure

```
src/
├── app/                 # Next.js App Router
│   ├── globals.css     # Global styles
│   ├── layout.tsx      # Root layout
│   └── page.tsx        # Home page
├── components/         # React components
│   └── TodoApp.tsx     # Main todo application
└── lib/               # Utilities
    └── api.ts         # API client and types
```

## Technologies Used

- **Next.js 14** - React framework with App Router
- **TypeScript** - Type safety
- **Tailwind CSS** - Utility-first CSS framework
- **Axios** - HTTP client for API requests
- **Lucide React** - Beautiful icons

## API Configuration

The API base URL is configured in `src/lib/api.ts`. Update the `API_BASE_URL` constant if your backend runs on a different port or domain:

```typescript
const API_BASE_URL = 'http://localhost:8080'
```