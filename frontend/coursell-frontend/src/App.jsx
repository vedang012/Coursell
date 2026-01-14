function App() {
  const courses = [
    {
      id: 1,
      title: "Full-Stack Web Development Bootcamp",
      instructor: "Sarah Chen",
      price: 49.99,
      rating: 4.8,
      students: 12453
    },
    {
      id: 2,
      title: "Machine Learning Fundamentals",
      instructor: "Alex Kumar",
      price: 59.99,
      rating: 4.9,
      students: 8921
    },
    {
      id: 3,
      title: "UI/UX Design Principles",
      instructor: "Emma Rodriguez",
      price: 39.99,
      rating: 4.7,
      students: 6734
    }
  ];

  const features = [
    {
      title: "Learn Real-World Skills",
      description: "Access courses built by industry practitioners, not theory-only academics.",
      icon: "📚"
    },
    {
      title: "Secure Payments",
      description: "Your transactions are protected with bank-grade encryption and security.",
      icon: "🔒"
    },
    {
      title: "Instructor Dashboards",
      description: "Track earnings, manage content, and engage with your students seamlessly.",
      icon: "📊"
    }
  ];

  return (
    <div className="min-h-screen bg-white">
      {/* Navbar */}
      <nav className="sticky top-0 z-50 bg-white border-b border-gray-200 shadow-sm">
        <div className="max-w-7xl mx-auto px-6 py-4">
          <div className="flex items-center justify-between">
            <div className="text-2xl font-bold text-gray-900">Coursell</div>
            <div className="hidden md:flex items-center space-x-8">
              <a href="#marketplace" className="text-gray-700 hover:text-gray-900 transition-colors">
                Marketplace
              </a>
              <a href="#teach" className="text-gray-700 hover:text-gray-900 transition-colors">
                Teach on Coursell
              </a>
              <a href="#login" className="text-gray-700 hover:text-gray-900 transition-colors">
                Login
              </a>
              <a
                href="#signup"
                className="px-6 py-2.5 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 transition-colors"
              >
                Sign Up
              </a>
            </div>
          </div>
        </div>
      </nav>

      {/* Hero Section */}
      <section className="max-w-7xl mx-auto px-6 py-24 md:py-32">
        <div className="text-center max-w-4xl mx-auto">
          <h1 className="text-5xl md:text-6xl font-bold text-gray-900 mb-6 leading-tight">
            Learn from creators. Build your future.
          </h1>
          <p className="text-xl text-gray-600 mb-12 leading-relaxed">
            Discover quality courses from real practitioners and build skills that matter in the real world.
          </p>
          <div className="flex flex-col sm:flex-row gap-4 justify-center">
            <a
              href="#courses"
              className="px-8 py-4 bg-indigo-600 text-white text-lg font-medium rounded-lg hover:bg-indigo-700 transition-colors shadow-md hover:shadow-lg"
            >
              Explore Courses
            </a>
            <a
              href="#instructor"
              className="px-8 py-4 bg-white text-indigo-600 text-lg font-medium rounded-lg border-2 border-indigo-600 hover:bg-indigo-50 transition-colors"
            >
              Become an Instructor
            </a>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="bg-gray-50 py-20">
        <div className="max-w-7xl mx-auto px-6">
          <div className="grid md:grid-cols-3 gap-8">
            {features.map((feature, index) => (
              <div
                key={index}
                className="bg-white p-8 rounded-2xl shadow-sm hover:shadow-md transition-shadow"
              >
                <div className="text-4xl mb-4">{feature.icon}</div>
                <h3 className="text-xl font-semibold text-gray-900 mb-3">
                  {feature.title}
                </h3>
                <p className="text-gray-600 leading-relaxed">
                  {feature.description}
                </p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Popular Courses Section */}
      <section id="courses" className="max-w-7xl mx-auto px-6 py-20">
        <div className="text-center mb-12">
          <h2 className="text-4xl font-bold text-gray-900 mb-4">
            Popular Courses
          </h2>
          <p className="text-lg text-gray-600">
            Join thousands of students learning from the best
          </p>
        </div>
        <div className="grid md:grid-cols-3 gap-8">
          {courses.map((course) => (
            <div
              key={course.id}
              className="bg-white rounded-2xl shadow-sm hover:shadow-lg transition-shadow overflow-hidden border border-gray-100"
            >
              <div className="h-48 bg-gray-100"></div>
              <div className="p-6">
                <h3 className="text-xl font-semibold text-gray-900 mb-2">
                  {course.title}
                </h3>
                <p className="text-gray-600 mb-4">by {course.instructor}</p>
                <div className="flex items-center justify-between">
                  <div className="flex items-center space-x-2">
                    <span className="text-yellow-500">★</span>
                    <span className="font-medium text-gray-900">
                      {course.rating}
                    </span>
                    <span className="text-gray-500 text-sm">
                      ({course.students.toLocaleString()})
                    </span>
                  </div>
                  <span className="text-2xl font-bold text-gray-900">
                    ${course.price}
                  </span>
                </div>
              </div>
            </div>
          ))}
        </div>
      </section>

      {/* Instructor Pitch Section */}
      <section id="instructor" className="bg-indigo-600 py-20">
        <div className="max-w-4xl mx-auto px-6 text-center">
          <h2 className="text-4xl font-bold text-white mb-6">
            Share your expertise with the world
          </h2>
          <p className="text-xl text-indigo-100 mb-10 leading-relaxed">
            Create your course, set your price, and earn while helping others grow their skills.
          </p>
          <a
            href="#teach"
            className="inline-block px-8 py-4 bg-white text-indigo-600 text-lg font-medium rounded-lg hover:bg-gray-50 transition-colors shadow-md hover:shadow-lg"
          >
            Start Teaching
          </a>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-gray-900 text-gray-400 py-12">
        <div className="max-w-7xl mx-auto px-6">
          <div className="grid md:grid-cols-4 gap-8 mb-8">
            <div>
              <h3 className="text-white font-semibold mb-4">Coursell</h3>
              <p className="text-sm">
                Learn from creators. Build your future.
              </p>
            </div>
            <div>
              <h4 className="text-white font-semibold mb-4">Platform</h4>
              <ul className="space-y-2 text-sm">
                <li><a href="#" className="hover:text-white transition-colors">Marketplace</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Pricing</a></li>
                <li><a href="#" className="hover:text-white transition-colors">About</a></li>
              </ul>
            </div>
            <div>
              <h4 className="text-white font-semibold mb-4">Support</h4>
              <ul className="space-y-2 text-sm">
                <li><a href="#" className="hover:text-white transition-colors">Help Center</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Contact</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Terms</a></li>
              </ul>
            </div>
            <div>
              <h4 className="text-white font-semibold mb-4">Teach</h4>
              <ul className="space-y-2 text-sm">
                <li><a href="#" className="hover:text-white transition-colors">Become an Instructor</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Resources</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Community</a></li>
              </ul>
            </div>
          </div>
          <div className="border-t border-gray-800 pt-8 text-center text-sm">
            <p>© 2026 Coursell. All rights reserved.</p>
          </div>
        </div>
      </footer>
    </div>
  );
}

export default App;
